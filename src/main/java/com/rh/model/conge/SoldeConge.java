package com.rh.model.conge;
// package com.rh.model;

// import java.math.BigDecimal;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToOne;
// // import jakarta.persistence.Table;

// // @Entity
// public class SoldeConge {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer idSolde;

//     private BigDecimal totalConges; // Total des congés alloués
//     private BigDecimal congésPrises; // Congés déjà pris
//     private BigDecimal congésRestants; // Congés restants

//     @OneToOne
//     @JoinColumn(name = "id_personnel")
//     private Personnel personnel;

//     // Getters and Setters
//     public Integer getIdSolde() {
//         return idSolde;
//     }

//     public void setIdSolde(Integer idSolde) {
//         this.idSolde = idSolde;
//     }

//     public BigDecimal getTotalConges() {
//         return totalConges;
//     }

//     public void setTotalConges(BigDecimal totalConges) {
//         this.totalConges = totalConges;
//     }

//     public BigDecimal getCongesPrises() {
//         return congésPrises;
//     }

//     public void setCongesPrises(BigDecimal congésPrises) {
//         this.congésPrises = congésPrises;
//     }

//     public BigDecimal getCongesRestants() {
//         return totalConges.subtract(congésPrises);
//     }

//     public void setCongesRestants(BigDecimal congésRestants) {
//         this.congésRestants = congésRestants;
//     }

//     public Personnel getPersonnel() {
//         return personnel;
//     }

//     public void setPersonnel(Personnel personnel) {
//         this.personnel = personnel;
//     }

//     public BigDecimal getCongésRestants() {
//         return congésRestants;
//     }

//     public void setCongésRestants(BigDecimal congésRestants) {
//         this.congésRestants = congésRestants;
//     }

// }

