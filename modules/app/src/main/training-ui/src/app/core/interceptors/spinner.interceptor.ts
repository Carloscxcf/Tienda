import { HttpInterceptorFn } from "@angular/common/http";
import { inject } from "@angular/core";
import { NgxSpinnerService } from "ngx-spinner";
import { finalize } from "rxjs";

export const spinnerInterceptor: HttpInterceptorFn = (req, next) => {
    const spinnerCC = inject(NgxSpinnerService);
    spinnerCC.show();
    return next(req).pipe(
        finalize(() => spinnerCC.hide())
    );
};
