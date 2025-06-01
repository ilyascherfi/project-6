import { Component, input, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ThemeProps } from '../../interfaces/theme-props.class';
import { SessionService } from '../../services/session.service';
import { UserService } from '../../services/user-service.service';

@Component({
  selector: 'app-theme',
  imports: [],
  templateUrl: './theme.component.html',
  styleUrl: './theme.component.scss'
})
export class ThemeComponent implements OnDestroy {

  themeProps = input.required<ThemeProps>();

  public onError: boolean = false;
  public subscription: Subscription | undefined;


  constructor(
    private sessionService: SessionService,
    private userService: UserService,
  ) { };

  ngOnDestroy(): void {
    this.unSubscribeObservableIfSubscription();
  }
  public unSubscribeObservableIfSubscription(): void {
    if (this.subscription != undefined) {
      this.subscription.unsubscribe()
    }
  }

  public onSubscribe(): void {
    this.unSubscribeObservableIfSubscription();
    this.subscription = this.userService.themeSubscribe(this.themeProps().theme.themeId).subscribe({
      next: () => {
        this.sessionService.addTheme(this.themeProps().theme);
      },
      error: (error: any) => {
        this.onError = true;
        console.log(error);
      },
    });
  }

  public onUnsubscribe(): void {
    this.unSubscribeObservableIfSubscription();
    this.subscription = this.userService.themeUnsubscribe(this.themeProps().theme.themeId).subscribe({
      next: () => {
        this.sessionService.removeTheme(this.themeProps().theme);
      },
      error: (error: any) => {
        this.onError = true;
        console.log(error);
      },
    });
  }

}
