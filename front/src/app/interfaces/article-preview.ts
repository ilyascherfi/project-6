import { CommentContent } from "./comment-content.interface";

export interface ArticlePreview {
    articleId: number;
    title: string;
    author: string;
    theme: string;
    date: Date;
    content: string;
    comments: CommentContent[];
}
