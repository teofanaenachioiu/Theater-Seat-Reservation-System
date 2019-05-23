import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-confirm',
  template: `
    <div class="modal-header">
      <h4 class="modal-title" id="modal-title">Show deletion</h4>
      <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p><strong>Are you sure you want to delete <span class="text-primary">{{name}}</span> show?</strong></p>
      <p>All information associated to this show will be permanently deleted.
        <span class="text-danger">This operation can not be undone.</span>
      </p>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-secondary" (click)="cancel()">Cancel</button>
      <button type="button" class="btn btn-danger" (click)="saveData()">Ok</button>
    </div>
  `
})

// (click)="modal.close('Ok click')
export class NgbdModalDeleteContent {
  @Input() name;
  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  constructor(public modal: NgbActiveModal) {}

  cancel() {
    this.passEntry.emit('cancel');
    this.modal.dismiss();
  }

  saveData() {
    this.passEntry.emit('delete');
    this.modal.close();
  }
}
