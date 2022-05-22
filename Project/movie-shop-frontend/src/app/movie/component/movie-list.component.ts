import {Component, OnInit} from "@angular/core";
import {MovieService} from "../service/movie.service";
import {Movie, MovieList} from "../model/movie.model";
import {Category} from "../../category/category.model";

@Component({
  selector: 'app-movie-list',
  template: `
    <div class="flex flex-row justify-between border-2 rounded-lg mt-2 p-2">
      <div class="text-2xl">{{'movie.list'| translate}}</div>
      <app-movie-search (search)="onSearch($event)"></app-movie-search>
    </div>
    <div *ngFor="let movie of movies" class="">
      <app-movie-item [movie]="movie"></app-movie-item>
    </div>
    <div *ngIf="movies.length === 0" class="border-2 rounded-md my-1 p-2 text-center text-2xl">
      {{'empty.list'|translate}}
    </div>
    <app-movie-pagination [currentPage]="currentPage" [total]="totalPages" (newPage)="onNewPage($event)"></app-movie-pagination>
  `
})
export class MovieListComponent implements OnInit {

  movies: Movie[] = []
  currentPage: number = 0
  totalPages: number = 0
  currentCategoryId: number = -1

  constructor(private movieService: MovieService) {
  }

  ngOnInit(): void {
    this.movieService.getMovieList()
      .subscribe(movieList => this.updateMovieList(movieList))
  }

  onSearch(category: Category) {
    this.currentCategoryId = category.id
    this.currentPage = 0
    this.movieService.getMovieList(this.currentPage, category.id)
      .subscribe(movieList => this.updateMovieList(movieList))
  }

  onNewPage(pageNumber: number) {
    this.currentPage = pageNumber
    this.movieService.getMovieList(pageNumber, this.currentCategoryId)
      .subscribe(movieList => this.updateMovieList(movieList))
  }

  private updateMovieList(moviesList: MovieList) {
    this.movies = moviesList.content
    this.currentPage = moviesList.number
    this.totalPages = moviesList.totalPages
  }
}
