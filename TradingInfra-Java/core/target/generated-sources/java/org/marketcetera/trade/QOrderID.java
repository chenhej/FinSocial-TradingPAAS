package org.marketcetera.trade;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderID is a Querydsl query type for OrderID
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QOrderID extends BeanPath<OrderID> {

    private static final long serialVersionUID = 316603545L;

    public static final QOrderID orderID = new QOrderID("orderID");

    public final StringPath mValue = createString("mValue");

    public QOrderID(String variable) {
        super(OrderID.class, forVariable(variable));
    }

    public QOrderID(Path<? extends OrderID> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderID(PathMetadata metadata) {
        super(OrderID.class, metadata);
    }

}

