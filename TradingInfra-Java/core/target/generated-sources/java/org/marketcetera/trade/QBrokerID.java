package org.marketcetera.trade;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBrokerID is a Querydsl query type for BrokerID
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QBrokerID extends BeanPath<BrokerID> {

    private static final long serialVersionUID = 363191556L;

    public static final QBrokerID brokerID = new QBrokerID("brokerID");

    public final StringPath mValue = createString("mValue");

    public QBrokerID(String variable) {
        super(BrokerID.class, forVariable(variable));
    }

    public QBrokerID(Path<? extends BrokerID> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBrokerID(PathMetadata metadata) {
        super(BrokerID.class, metadata);
    }

}

