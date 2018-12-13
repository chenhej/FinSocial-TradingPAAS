package org.marketcetera.trade;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportID is a Querydsl query type for ReportID
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QReportID extends BeanPath<ReportID> {

    private static final long serialVersionUID = -436547681L;

    public static final QReportID reportID = new QReportID("reportID");

    public final NumberPath<Long> mValue = createNumber("mValue", Long.class);

    public QReportID(String variable) {
        super(ReportID.class, forVariable(variable));
    }

    public QReportID(Path<? extends ReportID> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportID(PathMetadata metadata) {
        super(ReportID.class, metadata);
    }

}

