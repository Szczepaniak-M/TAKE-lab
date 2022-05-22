import {Component, OnInit} from "@angular/core";
import {faCartShopping, faFilm} from "@fortawesome/free-solid-svg-icons";
import {Router} from "@angular/router";
import {CartService} from "../../cart/service/cart.service";


@Component({
  selector: 'app-navbar',
  template: `
    <div class="grid place-content-center p-2 bg-cyan-400">
      <h1 class="italic text-slate-200 text-5xl mb-2">Groovy Movie Shop</h1>
      <div class="flex flex-row justify-around my-1">
        <div (click)="openList()" class="text-slate-200 hover:text-slate-300 text-2xl inline-block cursor-pointer">
          <fa-icon [icon]="faFilm"></fa-icon>
          Movie List
        </div>
        <div (click)="openCart()" class="text-slate-200 hover:text-slate-300 text-2xl inline-block cursor-pointer">
          <fa-icon [icon]="faCartShopping"></fa-icon>
          Cart
          <div class="border-2 rounded-full inline-block text-center w-7 h-7 leading-6">{{cartSize}}</div>
        </div>
      </div>
    </div>
  `
})
export class NavbarComponent implements OnInit {
  faFilm = faFilm;
  faCartShopping = faCartShopping;
  cartSize = 0

  constructor(private router: Router,
              private cartService: CartService) {
  }

  ngOnInit() {
    this.cartService.total$.subscribe(total => this.cartSize = total)
  }

  openList() {
    this.router.navigate(['/']).then()
  }

  openCart() {
    this.router.navigate(['/cart']).then()
  }
}
