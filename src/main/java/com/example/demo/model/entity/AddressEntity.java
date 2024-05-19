package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class AddressEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id_address;

   private Long agent_id;

   private String n_jalan;

   private String rt;

   private String rw;

   private String kota;

   private String provinsi;

   private String kode_pos;

   public AddressEntity() {

   }

   public AddressEntity(Long id_address, Long agent_id, String n_jalan, String rt, String rw, String kota,
         String provinsi,
         String kode_pos) {

      this.agent_id = agent_id;
      this.id_address = id_address;
      this.n_jalan = n_jalan;
      this.rt = rt;
      this.rw = rw;
      this.kota = kota;
      this.provinsi = provinsi;
      this.kode_pos = kode_pos;
   }

   public Long getId_address() {
      return id_address;
   }

   public void setId_address(Long id_address) {
      this.id_address = id_address;
   }

   public String getN_jalan() {
      return n_jalan;
   }

   public void setN_jalan(String n_jalan) {
      this.n_jalan = n_jalan;
   }

   public String getRt() {
      return rt;
   }

   public void setRt(String rt) {
      this.rt = rt;
   }

   public String getRw() {
      return rw;
   }

   public void setRw(String rw) {
      this.rw = rw;
   }

   public String getKota() {
      return kota;
   }

   public void setKota(String kota) {
      this.kota = kota;
   }

   public String getProvinsi() {
      return provinsi;
   }

   public void setProvinsi(String provinsi) {
      this.provinsi = provinsi;
   }

   public String getKode_pos() {
      return kode_pos;
   }

   public void setKode_pos(String kode_pos) {
      this.kode_pos = kode_pos;
   }

   public Long getAgent_id() {
      return agent_id;
   }

   public void setAgent_id(Long agent_id) {
      this.agent_id = agent_id;
   }

}
