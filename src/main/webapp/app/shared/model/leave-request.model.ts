import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';
import { ILeaveType } from 'app/shared/model/leave-type.model';
import { LeaveRequestStatus } from 'app/shared/model/enumerations/leave-request-status.model';

export interface ILeaveRequest {
  id?: number;
  startDate?: string;
  endDate?: string;
  status?: LeaveRequestStatus | null;
  reason?: string | null;
  employee?: IEmployee | null;
  leaveType?: ILeaveType | null;
}

export const defaultValue: Readonly<ILeaveRequest> = {};
