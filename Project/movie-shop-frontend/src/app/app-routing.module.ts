import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MovieListComponent} from "./movie/component/movie-list.component";
import {MovieDetailsComponent} from "./movie/component/movie-details.component";
import {CartComponent} from "./cart/component/cart.component";

const routes: Routes = [
  {path: '', component: MovieListComponent},
  {path: 'movies', component: MovieListComponent},
  {path: 'movies/:id', component: MovieDetailsComponent},
  {path: 'cart', component: CartComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
