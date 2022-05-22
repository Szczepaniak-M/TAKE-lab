import {Category} from "../../category/category.model";

export interface Movie {
  id: number;
  title: string;
  year: number;
  description?: string;
  category: Category;
  price: number;
}

export interface MovieList {
  totalPages: number,
  number: number,
  content: Movie[]
}
