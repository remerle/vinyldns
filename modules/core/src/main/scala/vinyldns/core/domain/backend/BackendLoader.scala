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

import cats.implicits._
import cats.effect.IO
import org.slf4j.LoggerFactory

object BackendLoader {

  private val logger = LoggerFactory.getLogger("BackendLoader")

  def load(configs: List[BackendProviderConfig]): IO[List[BackendProvider]] = {
    def loadOne(config: BackendProviderConfig): IO[BackendProvider] =
      for {
        _ <- IO(logger.error(s"Attempting to load backend ${config.className}"))
        provider <- IO(
          Class
            .forName(config.className)
            .getDeclaredConstructor()
            .newInstance()
            .asInstanceOf[BackendProviderLoader]
        )
        backend <- provider.load(config)
      } yield backend

    configs.traverse(loadOne)
  }
}
