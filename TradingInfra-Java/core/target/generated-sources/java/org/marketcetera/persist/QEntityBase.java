package org.marketcetera.persist;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEntityBase is a Querydsl query type for EntityBase
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QEntityBase extends EntityPathBase<EntityBase> {

    private static final long serialVersionUID = -313876556L;

    public static final QEntityBase entityBase = new QEntityBase("entityBase");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastUpdated = createDateTime("lastUpdated", java.util.Date.class);

    public final NumberPath<Integer> updateCount = createNumber("updateCount", Integer.class);

    public QEntityBase(String variable) {
        super(EntityBase.class, forVariable(variable));
    }

    public QEntityBase(Path<? extends EntityBase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEntityBase(PathMetadata metadata) {
        super(EntityBase.class, metadata);
    }

}

