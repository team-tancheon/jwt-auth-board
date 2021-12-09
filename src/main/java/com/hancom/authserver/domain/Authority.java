//package com.hancom.authserver.domain;
//
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.Collection;
//import java.util.List;
//
//
//@Entity
//@Table(name = "user_roles")
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter @Setter
//public class Authority implements GrantedAuthority {
//
////    public static final String ROLE_USER = "ROLE_USER";
////    public static final String ROLE_ADMIN = "ROLE_ADMIN";
//
//    @Id
//    private String id;
//
//    @NotNull
//    private String authority;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//}
