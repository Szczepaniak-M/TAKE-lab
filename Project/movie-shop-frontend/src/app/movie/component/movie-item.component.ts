import {Component, Input} from "@angular/core";
import {Movie} from "../model/movie.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-movie-item',
  template: `
    <div class="flex flex-row justify-between border-2 rounded-md my-1 p-2 hover:bg-slate-100 cursor-pointer"
         (click)="onClick($event)">
      <div>
        <div class="text-xl">{{movie.title}}</div>
        <div class="text-l">{{movie.year}}</div>
        <div class="text-l">{{movie.category.name}}</div>
      </div>
      <div class="flex flex-col justify-center items-center">
        <app-movie-add [movie]="movie"></app-movie-add>
        <div>{{'$' + movie.price}}</div>
      </div>
    </div>
  `
})
export class MovieItemComponent {

  @Input()
  movie!: Movie

  constructor(private router: Router) {
  }

  onClick(event: Event) {
    event.stopPropagation()
    this.router.navigate(['/', 'movies', this.movie?.id]).then()
  }
}
