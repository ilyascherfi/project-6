import { HttpEvent, HttpHandlerFn, HttpHeaders, HttpRequest } from "@angular/common/http";
import { inject } from "@angular/core";
import { SessionService } from "../services/session.service";
import { Observable } from "rxjs";

export function jwtInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const sessionService = inject(SessionService);

  if (!sessionService.isLogged || !sessionService._sessionInformation()?.token) {
    return next(req);
  }

  const headers = new HttpHeaders({
    Authorization: `Bearer ${sessionService._sessionInformation()!.token}`
  });


  const newReq = req.clone({ headers });


  return next(newReq);
}
