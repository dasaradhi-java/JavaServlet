package com.registerDetails;

import java.util.Date;

public class OrderEntity {
	 private int orderId;
	    private String productName;
	    private int orderQuantity;
	    private Date createDate;
	    private Date deliveryDate;
		public int getOrderId() {
			return orderId;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public int getOrderQuantity() {
			return orderQuantity;
		}
		public void setOrderQuantity(int orderQuantity) {
			this.orderQuantity = orderQuantity;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Date getDeliveryDate() {
			return deliveryDate;
		}
		public void setDeliveryDate(Date deliveryDate) {
			this.deliveryDate = deliveryDate;
		}
		@Override
		public String toString() {
			return "OrderEntity [orderId=" + orderId + ", productName=" + productName + ", orderQuantity="
					+ orderQuantity + ", createDate=" + createDate + ", deliveryDate=" + deliveryDate + "]";
		}
	
	    
	    
	    
}
