package com.io.ms.entities.login;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.ms.entities.login.Gender;
import com.io.ms.entities.login.Role;
import com.io.ms.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "code", unique = true, nullable = false, length = 40)
  private String code;

  @Column(name = "firstname", nullable = false, length = 50)
  private String firstname;

  @Column(name = "lastname", length = 50)
  private String lastname;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private Gender gender;
  @Email
  @Column(name = "email", nullable = false, length = 50)
  private String email;

  @Column(name = "password", nullable = false, length = 60)
  private String password;

  @Column(name = "contactnum1", nullable = false, length = 15)
  private String contactNum1;

  @Column(name = "contactnum2", nullable = false, length = 15)
  private String contactNum2;

  @Column(name = "country", nullable = false, length = 15)
  private String country;

  @Column(name = "state", nullable = false, length = 20)
  private String state;

  @Column(name = "city", nullable = false, length = 20)
  private String city;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  @Column(name = "dob")
  private LocalDate dob;

  @CreationTimestamp
  @Column(name = "createdAt",updatable = false)
  private LocalDate createdDate;

  @UpdateTimestamp
  @Column(name = "updatedAt",insertable = false)
  private LocalDate updatedDate;

  @Column(name = "linkdinid", length = 100)
  private String linkdinID;

  @Column(name = "facebookid", length = 100)
  private String facebookID;

  @Column(name = "instaid", length = 100)
  private String instaID;

  @Column(name = "pannum", nullable = false, length = 15)
  private String pannum;

  @Column(name = "address1", nullable = false, length = 60)
  private String address1;

  @Column(name = "address2", length = 60)
  private String address2;

  @Column(name = "pincode", nullable = false,length = 12)
  private String pincode;

  @Column(name = "profileactive", length = 3)
  private String profileActive;

  @Column(name = "profileNOTActiveUpdatedby", length = 50)
  private Long profileNOTActiveUpdatedby;

  @Column(name = "reportingmanagerId", length = 50)
  private Long reportingmanagerId;

  @Column(name = "reportingmanagerName", length = 100)
  private String reportingmanagerName;

  @Column(name = "nameofMyTeam", length = 50)
  private String nameofMyTeam;

  @Column(name = "cityAllocated", length = 300)
  private List<String> citiesAllocated;

  @Column(name = "schoolAllocated", length = 300)
  private List<Long> schoolAllocated;

  @Column(name = "imageName", length = 100)
  private String imageName;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


}
