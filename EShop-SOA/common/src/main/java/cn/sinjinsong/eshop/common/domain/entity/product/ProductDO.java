package cn.sinjinsong.eshop.common.domain.entity.product;

import cn.sinjinsong.eshop.common.enumeration.product.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "category")
public class ProductDO implements Serializable{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.name
     *
     * @mbggenerated
     */
    @NotNull
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.price
     *
     * @mbggenerated
     */
    @NotNull
    @Min(1)
    private Double price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.category
     *
     * @mbggenerated
     */
    @NotNull
    private ProductCategoryDO category;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.type
     *
     * @mbggenerated
     */
    @NotNull
    private ProductType type;
    @NotNull
    private String description;
    @JsonIgnore
    private Boolean isOnPromotion;
    private String imageUrl;
}