import { Component } from '@angular/core';

@Component({
  selector: 'hs-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'health-service-ui';
  welcomeMsg: string = 'Welcome to My Health Service';
  pageTitle: string = 'Health Service'
}
