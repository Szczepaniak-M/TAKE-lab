import {Component} from "@angular/core";
import {Movie} from "../../movie/model/movie.model";
import {CartService} from "../service/cart.service";
import {Router} from "@angular/router";

@Component({
  selector: 'cart',
  template: `
    <div class="text-center text-4xl border-2 round-md my-1 p-2">
      {{'cart.movie'| translate}}
    </div>
    <div *ngFor="let movie of movies" class="">
      <app-movie-item [movie]="movie"></app-movie-item>
    </div>
    <div *ngIf="movies.length === 0" class="border-2 rounded-md my-1 p-2 text-center text-2xl">
      {{'empty.cart'|translate}}
    </div>
    <div class="flex flex-row justify-between items-center border-2 rounded-md p-2">
      <div class="flex flex-row text-2xl">
        <div class="mr-2">
          {{'cart.summary' | translate}}:
        </div>
        <div>
          {{'$' + priceSum.toFixed(2)}}
        </div>
      </div>
      <button (click)="onConfirm()"
        class="border rounded-lg bg-cyan-400 hover:bg-cyan-500 text-slate-100 p-2 w-40 text-center cursor-pointer">
        {{'button.buy' | translate}}
      </button>
    </div>
  `
})
export class CartComponent {

  movies: Movie[] = []
  priceSum: number = 0

  constructor(private cartService: CartService,
              private router: Router) {
  }

  ngOnInit() {
    this.cartService.movies$.subscribe(movies => {
      this.movies = movies
      this.priceSum = this.movies.reduce((accumulator, movie) => accumulator + movie.price, 0)
    })
  }

  onConfirm() {
    this.cartService.resetState()
    this.router.navigate(['/']).then()
  }
}
