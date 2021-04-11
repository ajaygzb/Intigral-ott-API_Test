package com.intigral.ott.popcorn.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Creating POJO for validating Popcorn API Response Body.
 */
@Builder@Data@NoArgsConstructor@AllArgsConstructor@JsonIgnoreProperties(ignoreUnknown = true)
public class PopcornPromotionResponse {

   private List<Promotion> promotions;

    public List<Promotion> getPromotions() {
	return promotions;
}
	@Builder@Data@NoArgsConstructor@AllArgsConstructor@JsonIgnoreProperties(ignoreUnknown = true)
   public static class Promotion{
       String promotionId;
       Integer orderId;
       List<String>promoArea;
       String promoType;
       
       @JsonProperty(value="promotionId")
       public String getPromotionId() {
    	   System.out.println(promotionId);
		return promotionId;
	}
    @JsonProperty(value="orderId")   
	public Integer getOrderId() {
		return orderId;
	}
	public List<String> getPromoArea() {
		return promoArea;
	}
	public String getPromoType() {
		return promoType;
	}
	public boolean isShowPrice() {
		return showPrice;
	}
	public boolean isShowText() {
		return showText;
	}
	public LocalizedText getLocalizedTexts() {
		return localizedTexts;
	}
	public List<Properties> getProperties() {
		return properties;
	}
	
	boolean showPrice;
       public List<Image> getImages() {
		return images;
	}

	boolean showText;
       LocalizedText localizedTexts;
       List<Properties> properties;
       List<Image> images;


   }
	
    @Builder@Data@NoArgsConstructor@AllArgsConstructor@JsonIgnoreProperties(ignoreUnknown = true)
   public static class LocalizedText{
        public List<String> getAr() {
			return ar;
		}
		public List<String> getEn() {
			return en;
		}
		List<String> ar;
        List<String>en;
   }
    @Builder@Data@NoArgsConstructor@AllArgsConstructor@JsonIgnoreProperties(ignoreUnknown = true)
   public static class Properties{
        public String getYear() {
			return year;
		}
		public String getProgramType() {
			return programType;
		}
		public String getCurrency() {
			return currency;
		}
		public String getProgramAvailabilityId() {
			return programAvailabilityId;
		}
		public Rating getRating() {
			return rating;
		}
		public List<String> getCategories() {
			return categories;
		}
		public List<String> getGenre() {
			return genre;
		}
		public ProgramDescription getProgramDescription() {
			return programDescription;
		}
		public String getDuration() {
			return duration;
		}
		String year;
        String programType;
        String currency;
        String programAvailabilityId;
        Rating rating;
        List<String> categories;
        List<String> genre;
        ProgramDescription programDescription;
        String duration;
   }
    @Builder@Data@NoArgsConstructor@AllArgsConstructor@JsonIgnoreProperties(ignoreUnknown = true)
   public static class Rating{
        String scheme;
        String rating;
   }
    @Builder@Data@NoArgsConstructor@AllArgsConstructor@JsonIgnoreProperties(ignoreUnknown = true)
   public static class ProgramDescription{
        String ar;
        String en;
   }
    @Builder@Data@NoArgsConstructor@AllArgsConstructor@JsonIgnoreProperties(ignoreUnknown = true)
   public static class Image{
        public String getId() {
			return id;
		}
		public String getUrl() {
			return url;
		}
		public Integer getWidth() {
			return width;
		}
		public Integer getHeight() {
			return height;
		}
		String id;
        String url;
        Integer width;
        Integer height;
   }
}
