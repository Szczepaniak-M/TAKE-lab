import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {Movie} from "../../movie/model/movie.model";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private state: Movie[] = []
  private _movies$ = new BehaviorSubject<Movie[]>([]);
  private _total$ = new BehaviorSubject<number>(0);

  get movies$() {
    return this._movies$.asObservable();
  }

  get total$() {
    return this._total$.asObservable();
  }

  addMovie(movie: Movie) {
    this.state.push(movie)
    this._movies$.next(this.state)
    this._total$.next(this.state.length)
  }

  removeMovie(movieToRemove: Movie) {
    this.state = this.state.filter((movie) => movie.id != movieToRemove.id)
    this._movies$.next(this.state)
    this._total$.next(this.state.length)
  }

  isMovieInCart(movie: Movie) {
    return this.state.filter((movieInCart) => movieInCart.id === movie.id).length > 0
  }

  resetState() {
    this.state = []
    this._movies$.next([])
    this._total$.next(0)
  }

}
