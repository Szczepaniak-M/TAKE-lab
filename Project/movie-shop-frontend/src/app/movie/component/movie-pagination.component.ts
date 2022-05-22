import {Component, EventEmitter, Input, OnChanges, Output} from "@angular/core";

@Component({
  selector: 'app-movie-pagination',
  template: `
    <div class="flex flex-row justify-center">
      <ul class="flex flex-row border-2 rounded-md">
        <li>
          <button class="border-r-2 p-1 hover:bg-slate-100 disabled:bg-slate-100"
            [disabled]="currentPage === 0"
            (click)="goToPage(0)">
            &#60;&#60;
          </button>
        </li>
        <li>
          <button
            class="border-r-2 p-1 hover:bg-slate-100 disabled:bg-slate-100"
            [disabled]="currentPage === 0"
            (click)="goToPage(currentPage - 1)">
            &#60;
          </button>
        </li>
        <li *ngFor="let page of pages;">
          <button
            class="border-r-2 p-1"
            [class.bg-cyan-400]="page - 1 === currentPage"
            [class.hover:bg-cyan-500]="page - 1 === currentPage"
            [class.hover:bg-slate-100]="page - 1 !== currentPage"
            (click)="goToPage(page - 1)">
            {{page}}
          </button>
        </li>
        <li>
          <button
            class="border-r-2 p-1 hover:bg-slate-100 disabled:bg-slate-100"
            [disabled]="currentPage === total - 1 || total === 0"
            (click)="goToPage(currentPage + 1)">
            &#62;
          </button>
        </li>
        <li>
          <button
            class="p-1 hover:bg-slate-100 disabled:bg-slate-100"
            [disabled]="currentPage === total - 1 || total === 0"
            (click)="goToPage(total - 1)">
            &#62;&#62;
          </button>
        </li>
      </ul>
    </div>
  `
})
export class MoviePaginationComponent implements OnChanges {

  @Input()
  currentPage: number = 0
  @Input()
  total: number = 0

  pages: number[] = []

  @Output()
  newPage: EventEmitter<number> = new EventEmitter<number>()

  ngOnChanges() {
    this.pages = this.getPages(this.currentPage, this.total)
  }

  public goToPage(page: number): void {
    this.newPage.emit(page)
  }

  private getPages(current: number, total: number): number[] {
    if (current === 0 && total === 0) {
      return [1]
    }

    if (total <= 5) {
      return [...Array(total).keys()].map(_ => ++_)
    }

    if (current > 5) {
      if (current >= total - 2) {
        return [total - 4, total - 3, total - 2, total - 1, total]
      } else {
        return [current - 2, current - 1, current, current + 1, current + 2]
      }
    }

    return [1, 2, 3, 4, 5]

  }

}
