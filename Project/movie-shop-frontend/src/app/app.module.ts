import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MovieListComponent} from "./movie/component/movie-list.component";
import {MovieDetailsComponent} from "./movie/component/movie-details.component";
import {CartComponent} from "./cart/component/cart.component";
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {FooterComponent} from "./common/component/footer.component";
import {NavbarComponent} from "./common/component/navbar.component";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {MovieItemComponent} from "./movie/component/movie-item.component";
import {MovieSearchComponent} from "./movie/component/movie-search-component";
import {MoviePaginationComponent} from "./movie/component/movie-pagination.component";
import {MovieAddToCartComponent} from "./movie/component/movie-add-to-cart.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    MovieListComponent,
    MovieItemComponent,
    MovieDetailsComponent,
    MovieSearchComponent,
    MoviePaginationComponent,
    MovieAddToCartComponent,
    CartComponent,
    FooterComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    FontAwesomeModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}
