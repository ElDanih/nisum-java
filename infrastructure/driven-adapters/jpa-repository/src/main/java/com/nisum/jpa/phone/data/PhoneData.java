package com.nisum.jpa.phone.data;


import com.nisum.jpa.user.data.UserData;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "phones", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneData {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String citycode;
    private String contrycode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;
}
