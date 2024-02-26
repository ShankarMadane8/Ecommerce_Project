package com.Ecommerce.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * JPA specific extension of {@link org.springframework.data.repository.Repository}.
 *
 * @author sai
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Address {
		@GeneratedValue(strategy = GenerationType.AUTO)
     	@Id
		int id;
		int user_id;
		String Name;
		int pincode;
		String Address;
		String district;
		String state;
		String landmark;
		String addressType;
		long mobile;
		String locality;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public int getPincode() {
			return pincode;
		}
		public void setPincode(int pincode) {
			this.pincode = pincode;
		}
		public String getAddress() {
			return Address;
		}
		public void setAddress(String address) {
			Address = address;
		}
		public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getLandmark() {
			return landmark;
		}
		public void setLandmark(String landmark) {
			this.landmark = landmark;
		}
		public String getAddressType() {
			return addressType;
		}
		public void setAddressType(String addressType) {
			this.addressType = addressType;
		}
		public long getMobile() {
			return mobile;
		}
		public void setMobile(long mobile) {
			this.mobile = mobile;
		}
		public String getLocality() {
			return locality;
		}
		public void setLocality(String locality) {
			this.locality = locality;
		}
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}
		@Override
		public String toString() {
			return "Address [id=" + id + ", user_id=" + user_id + ", Name=" + Name + ", pincode=" + pincode
					+ ", Address=" + Address + ", district=" + district + ", state=" + state + ", landmark=" + landmark
					+ ", addressType=" + addressType + ", mobile=" + mobile + ", locality=" + locality + "]";
		}
		
		
		
}
