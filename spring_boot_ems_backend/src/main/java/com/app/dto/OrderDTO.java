package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.app.entities.DeliveryStatus;

public class OrderDTO {
	private long id;
	
	private int totalItems;
	
	private double totalOrderPrice;
	
	private long userId;
	
	private LocalDate placedOn;
	
	private DeliveryStatus status;
	
	private double deliveryPrice;
	
	private String paymentType;
	
	private double cartPrice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public double getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(double totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public LocalDate getPlacedOn() {
		return placedOn;
	}

	public void setPlacedOn(LocalDate placedOn) {
		this.placedOn = placedOn;
	}

	public DeliveryStatus getStatus() {
		return status;
	}

	public void setStatus(DeliveryStatus status) {
		this.status = status;
	}

	public double getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(double cartPrice) {
		this.cartPrice = cartPrice;
	}

	public OrderDTO(long id, int totalItems, double totalOrderPrice, long userId, LocalDate placedOn,
			DeliveryStatus status, double deliveryPrice, String paymentType, double cartPrice) {
		super();
		this.id = id;
		this.totalItems = totalItems;
		this.totalOrderPrice = totalOrderPrice;
		this.userId = userId;
		this.placedOn = placedOn;
		this.status = status;
		this.deliveryPrice = deliveryPrice;
		this.paymentType = paymentType;
		this.cartPrice = cartPrice;
	}

	public OrderDTO() {
		super();
	}
	
	
}
