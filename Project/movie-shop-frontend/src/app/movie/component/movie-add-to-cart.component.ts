import {Component, Input, OnChanges, SimpleChanges} from "@angular/core";
import {Movie} from "../model/movie.model";
import {CartService} from "../../cart/service/cart.service";

@Component({
  selector: 'app-movie-add',
  template: `
    <div *ngIf="!movieInCart" (click)="addMovieToCart($event)"
         class="border rounded-lg bg-cyan-400 hover:bg-cyan-500 text-slate-100 p-2 w-40 text-center cursor-pointer">
      {{'button.add'|translate}}
    </div>
    <div *ngIf="movieInCart" (click)="removeMovieFromCart($event)"
         class="border rounded-lg bg-red-400 hover:bg-red-500 text-slate-100 p-2 w-40 text-center cursor-pointer">
      {{'button.remove'|translate}}
    </div>
  `
})
export class MovieAddToCartComponent implements OnChanges {

  movieInCart = false

  @Input()
  movie!: Movie

  constructor(private cartService: CartService) {
  }

  ngOnChanges(_: SimpleChanges) {
    this.movieInCart = this.cartService.isMovieInCart(this.movie)
  }

  addMovieToCart(event: Event) {
    event.stopPropagation()
    this.movieInCart = true
    this.cartService.addMovie(this.movie)
  }

  removeMovieFromCart(event: Event) {
    event.stopPropagation()
    this.movieInCart = false
    this.cartService.removeMovie(this.movie)
  }
}
