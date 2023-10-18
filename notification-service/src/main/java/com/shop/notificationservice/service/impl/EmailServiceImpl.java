package com.shop.notificationservice.service.impl;

import com.shop.notificationservice.entity.Transaction;
import com.shop.notificationservice.entity.UserResponse;
import com.shop.notificationservice.exception.ConnectException;
import com.shop.notificationservice.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender mailSender;
  private final WebClient userClient;

  //    TODO: get documents from url -> attach to email -> send
  public void sendEmail(Transaction transaction)
      throws MessagingException, UnsupportedEncodingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    Long userId = Long.valueOf(transaction.getUserId());
    UserResponse userResponse = getUserById(userId);

    helper.setFrom("contact@shopme.com", "Shopme Support");
    helper.setTo(userResponse.getEmail());
    String subject = "Your order has been paid successfully";
    String content =
        "<p>Hello "
            + userResponse.getFirstName()
            + "</p>"
            + "<p>Your have paid "
            + transaction.getAmount()
            + " VND for the order "
            + transaction.getOrderId()
            + " at </p>"
            + transaction.getReceivedTime()
            + "<br>"
            + "<p>Note: If you do not perform this, please contact us.</p>";
    helper.setSubject(subject);
    helper.setText(content, true);
    mailSender.send(message);
  }

  private UserResponse getUserById(Long userId) {
    try {
      Mono<UserResponse> responseMono =
          userClient
              .get()
              .uri("/api/user/{id}", userId)
              .retrieve()
              .onStatus(
                  httpStatus -> !httpStatus.is2xxSuccessful(),
                  clientResponse -> handleErrorResponse(clientResponse.statusCode()))
              .bodyToMono(UserResponse.class);
      log.info("Get User successfully: " + responseMono);
      return responseMono.block();
    } catch (RuntimeException exception) {
      log.error(exception.getMessage());
      throw new ConnectException("Fail to get User information");
    }
  }

  private Mono<? extends Throwable> handleErrorResponse(HttpStatusCode statusCode) {
    return Mono.error(new ConnectException("Failed to fetch employee. Status code: " + statusCode));
  }

  @Override
  public void getEmail(String keySearch) {}

  @Override
  public void deleteEmail(String keySearch) {}
}
