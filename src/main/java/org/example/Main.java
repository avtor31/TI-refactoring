package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        User author = new User("Semen Semenov", "semenov@mail.com");
        User sponsor = new User("Sponsor Sponsorov", "sponsor@mail.com");
        User whoLikes = new User("Ivan Ivanov", "ivanov@mail.com");
        SponsoredPost sponsoredPost =
                new SponsoredPost(author, 1,
                        List.of(new Comment("Ok")), sponsor);

        LikeServiceImpl likeService = new LikeServiceImpl();
        likeService.like(sponsoredPost, whoLikes);
    }

    public interface LikeService {
        void like(Post post, User whoLikes);
    }

    public class LikeServiceImpl implements LikeService {
        private LikeDao likeDao;
        private NotificationService notificationService;

        public LikeServiceImpl() {
            this.likeDao = new LikeDaoPostgresImpl();
            this.notificationService = new NotificationServiceImpl();
        }

        @Override
        public void like(Post post, User whoLikes) {
            likeDao.save(post, whoLikes);
            notificationService.notify(post.getAuthor(), post, whoLikes);
            if (post instanceof SponsoredPost) {
                notificationService.notify(((SponsoredPost) post).getSponsor(), post, whoLikes);
            }
        }
    }

    private class Post {
        protected User author;
        protected int likes;
        protected List<Comment> comments;

        public Post(User author, int likes, List<Comment> comments) {
            this.author = author;
            this.likes = likes;
            this.comments = comments;
        }

        public Post() {
        }

        public User getAuthor() {
            return this.author;
        }

        public int getLikes() {
            return this.likes;
        }

        public List<Comment> getComments() {
            return this.comments;
        }

        public void setAuthor(User author) {
            this.author = author;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public void setComments(List<Comment> comments) {
            this.comments = comments;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Post)) return false;
            final Post other = (Post) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$author = this.getAuthor();
            final Object other$author = other.getAuthor();
            if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
            if (this.getLikes() != other.getLikes()) return false;
            final Object this$comments = this.getComments();
            final Object other$comments = other.getComments();
            if (this$comments == null ? other$comments != null : !this$comments.equals(other$comments)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Post;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $author = this.getAuthor();
            result = result * PRIME + ($author == null ? 43 : $author.hashCode());
            result = result * PRIME + this.getLikes();
            final Object $comments = this.getComments();
            result = result * PRIME + ($comments == null ? 43 : $comments.hashCode());
            return result;
        }

        public String toString() {
            return "Main.Post(author=" + this.getAuthor() + ", likes=" + this.getLikes() + ", comments=" + this.getComments() + ")";
        }
    }

    public class SponsoredPost extends Post {
        private User sponsor;

        public SponsoredPost(User author, int likes, List<Comment> comments, User sponsor) {
            super(author, likes, comments);
            this.sponsor = sponsor;
        }

        public SponsoredPost(User sponsor) {
            this.sponsor = sponsor;
        }

        public User getSponsor() {
            return this.sponsor;
        }

        public void setSponsor(User sponsor) {
            this.sponsor = sponsor;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof SponsoredPost)) return false;
            final SponsoredPost other = (SponsoredPost) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$sponsor = this.getSponsor();
            final Object other$sponsor = other.getSponsor();
            if (this$sponsor == null ? other$sponsor != null : !this$sponsor.equals(other$sponsor)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof SponsoredPost;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $sponsor = this.getSponsor();
            result = result * PRIME + ($sponsor == null ? 43 : $sponsor.hashCode());
            return result;
        }

        public String toString() {
            return "Main.SponsoredPost(sponsor=" + this.getSponsor() + ")";
        }
    }

    private class Comment {
        protected String comment;

        public Comment(String comment) {
            this.comment = comment;
        }

        public String getComment() {
            return this.comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Comment)) return false;
            final Comment other = (Comment) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$comment = this.getComment();
            final Object other$comment = other.getComment();
            if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Comment;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $comment = this.getComment();
            result = result * PRIME + ($comment == null ? 43 : $comment.hashCode());
            return result;
        }

        public String toString() {
            return "Main.Comment(comment=" + this.getComment() + ")";
        }
    }

    private class User {
        protected String name;
        protected String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return this.name;
        }

        public String getEmail() {
            return this.email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof User)) return false;
            final User other = (User) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$name = this.getName();
            final Object other$name = other.getName();
            if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
            final Object this$email = this.getEmail();
            final Object other$email = other.getEmail();
            if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof User;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $name = this.getName();
            result = result * PRIME + ($name == null ? 43 : $name.hashCode());
            final Object $email = this.getEmail();
            result = result * PRIME + ($email == null ? 43 : $email.hashCode());
            return result;
        }

        public String toString() {
            return "Main.User(name=" + this.getName() + ", email=" + this.getEmail() + ")";
        }
    }

    public interface NotificationService {
        public void notify(User target, Post post, User whoLikes);
    }

    public class NotificationServiceImpl implements NotificationService {
        @Override
        public void notify(User target, Post post, User whoLikes) {
            System.out.println("User: " + target + " will be notified " +
                    " that post: " + post + " was laked by: " + whoLikes);
        }
    }

    public class LikeDaoPostgresImpl implements LikeDao {
        public Like save(Like like) {
            System.out.println("Like saved in DB");
            return like;
        }

        @Override
        public Like save(Post post, User whoLike) {
            return new Like(post, whoLike);
        }
    }

    public interface LikeDao {
        Like save(Post post, User whoLike);
    }

    public class Like {
        private Post post;
        private User user;

        public Like(Post post, User whoLike) {
            this.post = post;
            this.user = whoLike;
        }

        public Post getPost() {
            return this.post;
        }

        public User getUser() {
            return this.user;
        }

        public void setPost(Post post) {
            this.post = post;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Like)) return false;
            final Like other = (Like) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$post = this.getPost();
            final Object other$post = other.getPost();
            if (this$post == null ? other$post != null : !this$post.equals(other$post)) return false;
            final Object this$user = this.getUser();
            final Object other$user = other.getUser();
            if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Like;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $post = this.getPost();
            result = result * PRIME + ($post == null ? 43 : $post.hashCode());
            final Object $user = this.getUser();
            result = result * PRIME + ($user == null ? 43 : $user.hashCode());
            return result;
        }

        public String toString() {
            return "Main.Like(post=" + this.getPost() + ", user=" + this.getUser() + ")";
        }
    }

}