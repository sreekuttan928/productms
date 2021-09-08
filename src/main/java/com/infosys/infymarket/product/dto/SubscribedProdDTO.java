package com.infosys.infymarket.product.dto;


import com.infosys.infymarket.product.entity.SubscribedProd;

public class SubscribedProdDTO {

	
	
	ProductDTO prodid;
	Integer quantity;
	String buyerid;

	public ProductDTO getProdid() {
		
		
		return prodid;
	}
	public void setProdid(ProductDTO prodid) {
		
		
		this.prodid = prodid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		
		
		this.quantity = quantity;
	}
	public String getBuyerid() {
		
		
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		
		
		this.buyerid = buyerid;
	}
	public SubscribedProdDTO() {
		
		
		super();

	}
	public SubscribedProdDTO(String buyerid, ProductDTO prodid, Integer quantity) {
		this();
		this.buyerid = buyerid;
		this.prodid = prodid;
		this.quantity = quantity;
	}
	// Converts Entity into DTO
		public static SubscribedProdDTO valueOf(SubscribedProd subscribedprod) {
			SubscribedProdDTO subscribedprodDTO = new SubscribedProdDTO();
			ProductDTO prodto = new ProductDTO();
			subscribedprodDTO.setBuyerid(subscribedprod.getBuyerid());
			subscribedprodDTO.setQuantity(subscribedprod.getQuantity());
			prodto.setProdid(subscribedprod.getProdid());
			
			subscribedprodDTO.setProdid(prodto);
			subscribedprodDTO.setQuantity(subscribedprod.getQuantity());
			return subscribedprodDTO;
		}
	@Override
	public String toString() {
		return "SubscribedProdDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", quantity=" + quantity + "]";
	}
	public SubscribedProd createSubscribedProd() {
		SubscribedProd subscribedprod = new SubscribedProd();
		subscribedprod.setBuyerid(this.getBuyerid());
		subscribedprod.setProdid(this.getProdid().prodid);
		subscribedprod.setQuantity(this.getQuantity());
        return subscribedprod;
    }
	
}
