package mybatis.entity;

import java.util.Date;

public class BlogContent {
    private Long id;

    private String title;

    private Date createTime;

    private Date modifyTime;

    private String content;

    public BlogContent(Long id, String title, Date createTime, Date modifyTime) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public BlogContent(Long id, String title, Date createTime, Date modifyTime, String content) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.content = content;
    }

    public BlogContent() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}