import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {

  private requestCount = 0;
  private hideTimeout: any;
  loading = signal<boolean>(false);

  show():void{
    // Clear any pending hide timeout
    if (this.hideTimeout) {
      clearTimeout(this.hideTimeout);
      this.hideTimeout = null;
    }
    
    this.requestCount++;
    if (this.requestCount === 1) {
      console.log('Loader show');
      this.loading.set(true);
    }
  }

  hide():void{
    this.requestCount--;
    if (this.requestCount <= 0) {
      this.requestCount = 0; // Ensure it doesn't go negative
      
      // Debounce hiding to prevent flickering when requests are sequential
      this.hideTimeout = setTimeout(() => {
        // Double-check that no new requests came in during the delay
        if (this.requestCount === 0) {
          console.log('Loader hide');
          this.loading.set(false);
        }
        this.hideTimeout = null;
      }, 1000); // 100ms delay
    }
  }
}
