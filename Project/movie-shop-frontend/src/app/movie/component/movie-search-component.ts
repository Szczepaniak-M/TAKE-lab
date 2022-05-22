import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {CategoryService} from "../../category/category.service";
import {Category} from "../../category/category.model";

@Component({
  selector: 'app-movie-search',
  template: `
    <div class="flex flex-row">
      <div class="mr-2">{{'category.filter'|translate}}</div>
      <select [ngModel]="selectedCategory" (ngModelChange)="onChange($event)" class="rounded-lg hover cursor-pointer">
        <option *ngFor="let category of categories"
                [ngValue]="category">
          {{category.name}}
        </option>
      </select>
    </div>
  `
})
export class MovieSearchComponent implements OnInit {

  categories: Category[] = []
  selectedCategory: Category = noCategory


  @Output()
  search: EventEmitter<Category> = new EventEmitter<Category>()

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.categoryService.getCategoryList()
      .subscribe(categories => this.categories = [noCategory, ...categories])
  }

  onChange($event: Category) {
    this.search.next($event);
  }
}

const noCategory: Category = {
  id: -1,
  name: 'All'
}

