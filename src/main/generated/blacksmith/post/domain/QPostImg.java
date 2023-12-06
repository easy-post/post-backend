package blacksmith.post.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostImg is a Querydsl query type for PostImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostImg extends EntityPathBase<PostImg> {

    private static final long serialVersionUID = -1342694083L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostImg postImg = new QPostImg("postImg");

    public final blacksmith.post.domain.base.QBaseEntity _super = new blacksmith.post.domain.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath path = createString("path");

    public final QPost post;

    public QPostImg(String variable) {
        this(PostImg.class, forVariable(variable), INITS);
    }

    public QPostImg(Path<? extends PostImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostImg(PathMetadata metadata, PathInits inits) {
        this(PostImg.class, metadata, inits);
    }

    public QPostImg(Class<? extends PostImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

