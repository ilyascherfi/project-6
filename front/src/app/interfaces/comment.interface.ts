import { Article } from "./article.interface";
import { User } from "./user.interface";

export interface Comment {
    commentId?: number;
    article: Article;
    user: User;
    content: string;
    createdAt?: string;
}
