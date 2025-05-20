import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon'
import { MatCardModule } from '@angular/material/card';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { Subscription } from 'rxjs';
import { RegisterRequest } from '../interfaces/registerRequest.interface';

@Component({
  selector: 'app-register',
  imports: [
    MatCardModule,
    MatIconModule,
    RouterLink,
    ReactiveFormsModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
  providers: [AuthService]
})
export class RegisterComponent implements OnInit, OnDestroy {

  private authService = inject(AuthService);
  constructor(
    private fb: FormBuilder,
    private router: Router){}


    public onError = false;
    public subscription: Subscription | undefined;
    public registerForm!: FormGroup;

  ngOnInit():void {
    this.registerForm = this.fb.group({
      username: [
        '',
        [
          Validators.required,
          Validators.min(2),
          Validators.max(30)
        ]
      ],
      email: [
        '',
        [
          Validators.required,
          Validators.email
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

  }

   public onSubmit(): void {
    const registerRequest = this.registerForm.value as RegisterRequest;
    this.subscription = this.authService.register(registerRequest).subscribe({
      next: (_: void) => {
        this.router.navigate(['/auth/login'])
      },
      error: _ => {
        debugger;
        this.onError = true
      },
    }
    );
  }

}
