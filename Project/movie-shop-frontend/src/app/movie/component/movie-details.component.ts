import {Component} from "@angular/core";
import {MovieService} from "../service/movie.service";
import {Movie} from "../model/movie.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'movie-details',
  template: `
    <div class="flex flex-col my-2 p-2 border-2 rounded-lg">
      <div class="flex flex-row justify-between">
        <div class="flex flex-col">
          <div class="text-4xl">
            {{movie.title}}
          </div>
          <div>
            {{'movie.year'|translate}}: {{movie.year}}
          </div>
          <div>
            {{'movie.category'|translate}}: {{movie.category.name}}
          </div>
        </div>
        <div class="flex flex-col justify-center items-center">
          <app-movie-add [movie]="movie"></app-movie-add>
          <div>
            {{'$' + movie.price}}
          </div>
        </div>
      </div>
      <div class="text-2xl mt-3 mb-1">
        {{'movie.description'|translate}}
      </div>
      <div>
        {{movie.description}}
      </div>
    </div>
  `
})
export class MovieDetailsComponent {
  movie: Movie = {
    id: 0,
    title: '',
    year: 0,
    category: {
      id: 0,
      name: '',
    },
    price: 0,
    description: ''
  }

  constructor(private movieService: MovieService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    const movieId = +this.route.snapshot.paramMap.get('id')!;
    this.movieService.getMovieDetails(movieId)
      .subscribe(movie => this.movie = movie);
  }

}
