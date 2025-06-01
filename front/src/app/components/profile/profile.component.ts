import { Component, computed, OnDestroy, OnInit, Signal, signal } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SessionService } from '../../services/session.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { map, Observable, Subscription } from 'rxjs';
import { SessionInformation } from '../../interfaces/session-information.class';
import { ThemeProps } from '../../interfaces/theme-props.class';
import { Theme } from '../../interfaces/theme.class';
import { UserService } from '../../services/user-service.service';
import { ThemeComponent } from '../theme/theme.component';
import { ModifyRequest } from '../../interfaces/modify-request.interface';
import { ModifyNoPassword } from '../../interfaces/modify-no-password.class';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    HeaderComponent,
    ReactiveFormsModule,
    ThemeComponent,
    MatSnackBarModule,
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit, OnDestroy {

  constructor(
    private fb: FormBuilder,
    private sessionService: SessionService,
    private userService: UserService,
    private matSnackBar: MatSnackBar) { }

  public onError = false;
  public subscription: Subscription | undefined;
  public _sessionInformation = signal<SessionInformation | undefined>(undefined);
  public form!: FormGroup;
  public isPasswordEmpty: boolean = true;

  public _themesProps: Signal<ThemeProps[]> = signal([]);


  ngOnInit(): void {
    this._sessionInformation = this.sessionService._sessionInformation;
    this._themesProps = computed(() =>
      this.themesToThemesProps(this._sessionInformation()!.themes)
    )

    this.form = this.fb.group({
      username: [
        this._sessionInformation()!.username,
        [
          Validators.required,
          Validators.min(2),
        ]
      ],
      email: [
        this._sessionInformation()!.email,
        [
          Validators.required,
          Validators.email
        ]
      ],
      password: [
        '',
      ]
    });

    this.form.controls['password'].valueChanges.subscribe((passwordValue: String) => {
      if (passwordValue.length > 0) {
        this.isPasswordEmpty = false
      }
    })
  }

  ngOnDestroy(): void {
    if (this.subscription != undefined) {
      this.subscription.unsubscribe()
    }
  }

  public onSubmit(): void {
    const request = this.form.value as ModifyRequest;
    request.id = this._sessionInformation()!.id;

    if (!this.isPasswordEmpty) {
      this.subscription = this.userService.modifyProfile(request).subscribe({
        next: () => {
          this._sessionInformation()!.email = request.email;
          this._sessionInformation()!.username = request.username;
          this.sessionService.logIn(this._sessionInformation()!);
          this.matSnackBar.open("Modification successfull !", 'Close', { duration: 3000 });
        },
        error: (error: any) => {
          this.onError = true;
          console.log(error);
        },
      });
    }

    else {
      const requestNoPassWord = new ModifyNoPassword(request.id, request.username, request.email)
      this.subscription = this.userService.modifyProfileNoPassword(requestNoPassWord).subscribe({
        next: () => {
          this._sessionInformation()!.email = request.email;
          this._sessionInformation()!.username = request.username;
          this.sessionService.logIn(this._sessionInformation()!);
          this.matSnackBar.open("Modification successfull !", 'Close', { duration: 3000 });
        },
        error: (error: any) => {
          this.onError = true;
          console.log(error);
        },
      });
    }
  }

  public onDisconnect(): void {
    this.sessionService.logOut();
  }

  private themesToThemesProps(themes: Theme[]): ThemeProps[] {
    let themesProps: ThemeProps[] = themes.map(theme => {
      return new ThemeProps(theme, true)
    })
    return themesProps;
  }

}
