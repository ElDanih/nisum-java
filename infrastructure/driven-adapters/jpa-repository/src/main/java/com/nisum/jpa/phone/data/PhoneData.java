package com.nisum.jpa.phone.data;


import com.nisum.jpa.user.data.UserData;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "phones")
public class PhoneData {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String cityCode;
    private String contryCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;
}
