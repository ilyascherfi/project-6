import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { NgIf } from '@angular/common';
import { AuthService } from '../../../services/auth.service';
import { Subscription } from 'rxjs';
import { AuthSuccess } from '../interfaces/authSuccess.interface';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { SessionService } from '../../../services/session.service';
import { UserInformation } from '../../../interfaces/user-information';
import { SessionInformation } from '../../../interfaces/session-information.class';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatCardModule,
    MatIconModule,
    RouterLink,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit, OnDestroy {

  private authService = inject(AuthService);
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  public onError = false;
  public loginSubscription: Subscription | undefined;
  public authenticateSubscription: Subscription | undefined;
  public loginForm!: FormGroup;

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      usernameOrEmail: [
        '',
        [
          Validators.required,
          Validators.min(2),
        ]
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.min(8),
          Validators.max(40)
        ]
      ]
    });

  }
  ngOnDestroy(): void {
    if (this.loginSubscription != undefined) {
      this.loginSubscription.unsubscribe()
    }
  }

  public onSubmit(): void {
    const loginRequest = this.loginForm.value as LoginRequest;
    this.loginSubscription = this.authService.login(loginRequest).subscribe({
      next: (tokenObject: AuthSuccess) => {
        localStorage.setItem('jwtToken', tokenObject.token);
        this.authenticateUser(tokenObject.token);
      },
      error: _ => {
        localStorage.removeItem('jwtToken');
        this.onError = true;
      },
    }
    );
  }

    public authenticateUser(token: string): void {
    this.authenticateSubscription = this.authService.authenticate(token).subscribe({
      next: (user: UserInformation) => {
        let sessionInformation: SessionInformation = new SessionInformation(
          token, user.id, user.username, user.email, user.themes
        )
        this.sessionService.logIn(sessionInformation);
        this.router.navigate(['/articles']);
      },
      error: _ => {
        localStorage.removeItem('jwtToken');
        this.onError = true
      },
    }
    );
  }
}
