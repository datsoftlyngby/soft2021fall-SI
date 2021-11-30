package dk.dd.serviceb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Order
{
            private String id;
            private User user;
            private Product product;
            private double price;
}

