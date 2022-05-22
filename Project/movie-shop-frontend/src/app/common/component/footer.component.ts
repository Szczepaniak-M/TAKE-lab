import {Component} from "@angular/core";

@Component({
  selector: 'app-footer',
  template: `
    <div class="grid place-content-center h-5 h-min mt-2">
      <div>{{'common.footer' | translate}}</div>
    </div>
  `
})
export class FooterComponent {
}
