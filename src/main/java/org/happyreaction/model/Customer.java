package org.happyreaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.happyreaction.model.base.BaseEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Customer domain model.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@JsonIgnoreProperties(value = { "accounts", "city", "perks" })
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private static final int MAX_PHONE_NUMBER_LENGTH = 10;

    private static final int MIN_PHONE_NUMBER_LENGTH = 7;

    /** Customer name. */
    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String name;

    /** Customer's accounts. */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;

    /** Customer city. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID")
    private City city;

    /** Customer address. */
    @Column(name = "ADDRESS")
    private String address;

    /** Customer email. */
    @Email
    @Column(name = "EMAIL")
    private String email;

    /** Customer phone number. */
    @Length(min = MIN_PHONE_NUMBER_LENGTH, max = MAX_PHONE_NUMBER_LENGTH)
    @Column(name = "PHONE")
    private String phone;

    /** Customer age. */
    @Column(name = "AGE")
    private Integer age;

    @Column(name = "TESTDATETIME")
    private LocalDateTime testDateTime;

    @Column(name = "TESTDATE")
    private LocalDate testDate;

    /** Customer perks. */
    @ManyToMany
    @JoinTable(name = "CUSTOMER_CUSTOMERPERK", joinColumns = @JoinColumn(name = "CUSTOMER_ID"), inverseJoinColumns = @JoinColumn(name = "PERK_ID"))
    private List<CustomerPerk> perks;

}