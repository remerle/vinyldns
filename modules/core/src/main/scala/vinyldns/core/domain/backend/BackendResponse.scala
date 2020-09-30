/*
 * Copyright 2018 Comcast Cable Communications Management, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vinyldns.core.domain.backend

/* Response from applying a change to a backend */
sealed trait BackendResponse
object BackendResponse {
  /* Indicates the backend request worked properly */
  final case class NoError(message: String) extends BackendResponse

  /* Indicates there was a failure that maybe recoverable with a try again */
  final case class Retry(message: String) extends BackendResponse
}
