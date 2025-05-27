import { Theme } from "./theme.interface";

export class ThemeProps {
  constructor(
    public theme: Theme,
    public isSubscribed: boolean
  ) { }
}
