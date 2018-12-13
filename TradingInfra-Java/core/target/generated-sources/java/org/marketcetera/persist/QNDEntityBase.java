package org.marketcetera.persist;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNDEntityBase is a Querydsl query type for NDEntityBase
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QNDEntityBase extends EntityPathBase<NDEntityBase> {

    private static final long serialVersionUID = -1327501846L;

    public static final QNDEntityBase nDEntityBase = new QNDEntityBase("nDEntityBase");

    public final QEntityBase _super = new QEntityBase(this);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Integer> updateCount = _super.updateCount;

    public QNDEntityBase(String variable) {
        super(NDEntityBase.class, forVariable(variable));
    }

    public QNDEntityBase(Path<? extends NDEntityBase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNDEntityBase(PathMetadata metadata) {
        super(NDEntityBase.class, metadata);
    }

}

