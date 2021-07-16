import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {UserService} from "../user.service";
import {IUser} from "../user";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  templateUrl: './userDetail.component.html',
  styleUrls: ['./userDetail.component.css']
})
export class UserDetailComponent implements OnInit {

  private sub!: Subscription;
  userDetail: IUser | undefined;
  errorMessage: string = '';

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    console.log('User component initialized!!!')
    let userId = this.route.snapshot.paramMap.get('userId');
    this.sub = this.userService.getUserDetail(userId).subscribe({
      next: userDetail => {
        this.userDetail = userDetail;
      },
      error: err => this.errorMessage = err
    });
  }

  onBack() {
    this.router.navigate(['/appointments'])
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
