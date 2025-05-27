import { Component, computed, inject, Signal, signal } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { Theme } from '../../interfaces/theme.class';
import { SessionService } from '../../services/session.service';
import { ThemeService } from '../../services/theme.service';
import { ThemeProps } from '../../interfaces/theme-props.class';
import { ThemeComponent } from '../theme/theme.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-themes',
  imports: [
    ThemeComponent,
    HeaderComponent,
  ],
  templateUrl: './themes.component.html',
  styleUrl: './themes.component.scss'
})
export class ThemesComponent {

  private sessionService: SessionService = inject(SessionService)
  private themeService: ThemeService = inject(ThemeService)

  public onError: Boolean = false;
  public subscription!: Subscription;
  private themes$: Observable<Theme[]> = this.themeService.getThemes();
  private themes!: Theme[];
  public _themesProps: Signal<ThemeProps[]> = signal([]);

  ngOnInit(): void {
    this.subscription = this.themes$.subscribe(themes => {
      this.themes = themes
      this.init_themesProps();
    })
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private init_themesProps(): void {
    this._themesProps = computed(() =>
      this.themes.map(
        (theme) => {
          return this.themeToThemeProps(theme, this.customIncludes(this.sessionService._sessionInformation()!.themes, theme))  //Out of the array if already subscribed by the user
        }
      )
    )
  }

  private equalityCheck(theme1: any, theme2: any) {
    const keys1 = Object.keys(theme1);
    const keys2 = Object.keys(theme2);
    if (keys1.length !== keys2.length) {
      return false;
    }
    for (const key of keys1) {
      if (theme1[key] !== theme2[key]) {
        return false;
      }
    }
    return true;
  }

  private customIncludes(themes: Theme[], theme: Theme): boolean {
    let bool: boolean = false;
    themes.forEach(t => {
      if (this.equalityCheck(t, theme)) {
        bool = true
      }
    })
    return bool;
  }

  private themeToThemeProps(theme: Theme, isSubscribed: boolean): ThemeProps {
    return new ThemeProps(theme, isSubscribed)
  }
}
