package org.fetch.plugin

trait FetchPlugin {
      def name: String
      def parameters: List[String]
      def description: String
}
