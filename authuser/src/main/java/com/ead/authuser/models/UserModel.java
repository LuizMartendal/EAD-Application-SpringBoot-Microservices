package com.ead.authuser.models;

import com.ead.authuser.dtos.UserEventDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_USER")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @JsonIgnore
    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 20)
    private String cpf;

    @Column
    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;

    public UserEventDto convertToUserEventDto() {
        UserEventDto userEventDto = new UserEventDto();
        BeanUtils.copyProperties(this, userEventDto);
        userEventDto.setUserType(this.getUserType().toString());
        userEventDto.setUserStatus(this.getUserStatus().toString());
        return userEventDto;
    }

}
